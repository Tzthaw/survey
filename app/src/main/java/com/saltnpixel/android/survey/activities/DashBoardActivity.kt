package com.saltnpixel.android.survey.activities

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import com.mmgoogleexpert.ptut.shared.ui.BaseActivity
import com.saltnpixel.android.shared.data.EmptyError
import com.saltnpixel.android.shared.data.FailError
import com.saltnpixel.android.shared.data.ResultError
import com.saltnpixel.android.shared.data.ServerError
import com.saltnpixel.android.survey.R
import com.saltnpixel.android.survey.entities.DashBoardData
import com.saltnpixel.android.survey.mvp.Injection
import com.saltnpixel.android.survey.mvp.presenters.DashBoardPresenter
import com.saltnpixel.android.survey.mvp.views.DashBoardView
import com.saltnpixel.android.survey.utils.*
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : BaseActivity(), DashBoardView,View.OnClickListener {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, DashBoardActivity::class.java)
        }
    }
    private var isUserOld:Boolean=false
    private var accessToken:String=""
    private val dashViewModel by lazyAndroid {
        ViewModelProviders.of(this, Injection.provideViewModelFactoryDashBoard(DashBoardPresenter()))
            .get(DashBoardPresenter::class.java)
    }

    private val buttonList by lazyAndroid {
        ArrayList<Button>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        dashViewModel.initPresenter(this)
        dashViewModel.onNotifyDashBoardData()
        dashViewModel.errorLD.observe(this, this)
        dashViewModel.dashResultLD?.observe(this, Observer {
            if(it!=null){
                setAllData(it)
            }else{
                AlertDialogHelper.displayAlert(this,"No Data Found")
            }
        })

        //custom box generate for welcome survey
        checkBoxGenerate()
        radioGenerate()

        btnLogout.setOnClickListener(this)
        btnToSurvey.setOnClickListener(this)
        btnSurveyList.setOnClickListener(this)
        btnProfile.setOnClickListener(this)
        btnWelcomeSurvey.setOnClickListener(this)
        vgToProfile.setOnClickListener(this)
    }

    fun getAccessData(){
        val context = this
        val sharedPref = context.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )

        val defaultValue = resources.getString(R.string.saved_access_token_default_key)
        accessToken = sharedPref.getString(getString(R.string.saved_access_token), defaultValue)
        val editor = sharedPref.edit()

        //Remove stored welcome survey answer key in case user didn't finish
        editor.remove(WELCOME_SURVEY_FIRST_ANSWER_KEY)
        editor.remove(WELCOME_SURVEY_SECOND_ANSWER_KEY)
        editor.remove(WELCOME_SURVEY_THIRD_ANSWER_KEY)
        editor.remove(WELCOME_SURVEY_FOURTH_ANSWER_KEY)
        editor.remove(WELCOME_SURVEY_FIFTH_ANSWER_KEY)
        editor.remove(SAVED_DIVISOIN_KEY)
        editor.remove(SAVED_CITY_KEY)
        editor.commit()
    }

    private fun checkBoxGenerate(){
        val cb = CheckBox(this)
        cb.setButtonDrawable(R.drawable.checkbox_selector)
        cb.text = "I'm dynamic CB!"
        cb.tag = 34
        radioGroup.addView(cb)
        buttonList.add(cb)
    }

    private fun radioGenerate(){
        val rb = RadioButton(this)
        rb.text = "I'm dynamic Radio!"
        radioGroup.addView(rb)
        radioGroup.checkedRadioButtonId

    }

    @SuppressLint("SetTextI18n")
    fun setAllData(data: DashBoardData) {
        run {
            isUserOld = data.userCheck.equals("old")
            val userName = data.userName
            val surveyLimit = data.surveyLimit
            val points = data.points

            txtUserName.text = "Hi $userName"
            txtScorePoints.text = "You have $points points."
            //TODO check later for limit of survey 24 hours
            if (isUserOld) {
                txtUserGreetFirstTime.visibility = View.INVISIBLE
                txtUserGreetOne.visibility = View.VISIBLE
                txtUserGreetTwo.visibility = View.VISIBLE
                // check is survey limit or not
                if (surveyLimit.equals("no")) {
                    txtUserGreetOne.text = "Welcome Back"
                    txtUserGreetTwo.text = "New survey is available\nright now."
                    btnToSurvey.visibility = View.VISIBLE
                } else {
                    txtUserGreetOne.text = "Come Back!"
                    txtUserGreetTwo.text = "New Survey will be available\nwithin 24 hours."
                    btnToSurvey.visibility = View.INVISIBLE
                }
            } else {
                txtUserGreetFirstTime.visibility = View.VISIBLE
                txtUserGreetOne.visibility = View.INVISIBLE
                txtUserGreetTwo.visibility = View.INVISIBLE
                btnToSurvey.visibility = View.VISIBLE
            }
        }
    }

    override fun onChanged(error: ResultError?) {
        error.let {
            when (it) {
                is EmptyError -> {
                    AlertDialogHelper.displayAlert(this, EMPTY_ERROR)
                }
                is FailError -> {
                    AlertDialogHelper.displayAlert(this, FAILURE_ERROR)
                }
                is ServerError -> {
                    AlertDialogHelper.displayAlert(this, SERVER_ERROR)
                }
            }
        }
    }

    override fun onClick(v: View?) {
//        btnLogout.setOnClickListener(this)
//        btnToSurvey.setOnClickListener(this)
//        btnSurveyList.setOnClickListener(this)
//        btnProfile.setOnClickListener(this)
//        btnWelcomeSurvey.setOnClickListener(this)
//        vgToProfile.setOnClickListener(this)
        when(v?.id){
            R.id.btnLogout ->{
                val sharedPref = applicationContext.getSharedPreferences(
                    getString(R.string.preference_file_key), Context.MODE_PRIVATE
                )
                val editor = sharedPref.edit()
                editor.remove(getString(R.string.saved_access_token))
                editor.commit()
                startActivity(MainActivity.newIntent(this))
                finish()
            }
            R.id.btnToSurvey->{
                if (isUserOld) {
//                    val intent = Intent(this@DashBoardActivity, CategoryListActivity::class.java)
//                    startActivity(intent)
                } else {
                    //Flow dashboard>WelcomeSecond>welcomeFirst>WelcomeThird....
//                    val intent = Intent(this@DashBoardActivity, WelcomeSurveySecondActivity::class.java)
//                    startActivity(intent)
                }
            }
            R.id.btnSurveyList->{
//                val intent = Intent(this@DashBoardActivity, CategoryListActivity::class.java)
//                startActivity(intent)
            }
            R.id.btnProfile->{
//                val intent = Intent(this@DashBoardActivity, ProfileActivity::class.java)
//                startActivity(intent)
            }
            R.id.btnWelcomeSurvey->{
//                val intent = Intent(this@DashBoardActivity, WelcomeSurveySecondActivity::class.java)
//                startActivity(intent)
            }
            R.id.vgToProfile->{
//                val intent = Intent(this@DashBoardActivity, TabTestActivity::class.java)
//                startActivity(intent)
            }
        }
    }

}