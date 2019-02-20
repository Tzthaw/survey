package com.saltnpixel.android.survey.utils

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

//Background Executor
val threadCt = Runtime.getRuntime().availableProcessors() + 1
val executor = Executors.newFixedThreadPool(threadCt)!!
val scheduler = Schedulers.from(executor)

//Lazy mode with ThreadSafe initialize
fun <T> lazyAndroid(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

fun EditText.validate(validator: (String) -> Boolean, message: String) {
    this.afterTextChanged {
        this.error = if (validator(it)) null else message
    }
    this.error = if (validator(this.text.toString())) null else message
}

//EditText Extensions
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}

fun String.isValidEmail(): Boolean
        = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidText(): Boolean
        = this.isNotEmpty() && this.isNotBlank()

fun String.isValidPhoneNumber(): Boolean
        = this.isNotEmpty() &&
        Patterns.PHONE.matcher("+959[0-9]{9}").matches()

//parent layout inflate
fun ViewGroup.inflate(layoutId: Int): View = LayoutInflater.from(this.context).inflate(layoutId, this, false)

//Recyclerview Extension
fun RecyclerView.setUpRecycler(context: Context){
    hasFixedSize()
    layoutManager= LinearLayoutManager(context)
}
