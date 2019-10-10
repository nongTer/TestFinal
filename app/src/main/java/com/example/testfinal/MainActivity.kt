package com.example.testfinal

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var btudelet:Button
    lateinit var btuadd:Button
    lateinit var viewall:Button

    lateinit var edit_id:EditText
    lateinit var edit_name:EditText
    lateinit var edit_last:EditText
    lateinit var edit_number:EditText

    lateinit var myDb : DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btuadd = findViewById(R.id.btu_add)
        btudelet = findViewById(R.id.btu_dalete)
        viewall = findViewById(R.id.viewall)
        edit_id = findViewById(R.id.edit_id)
        edit_name = findViewById(R.id.edit_name)
        edit_last = findViewById(R.id.edit_last)
        edit_name = findViewById(R.id.btu_add)
        edit_number = findViewById(R.id.edit_number)

        myDb = DatabaseHelper(this)



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)


            btuadd=findViewById(R.id.btu_add)
            btudelet = findViewById(R.id.btu_dalete)
            viewall = findViewById(R.id.viewall)
            edit_id = findViewById(R.id.edit_id)
            edit_name= findViewById(R.id.edit_name)

            edit_last = findViewById(R.id.edit_last)
            edit_number = findViewById(R.id.edit_number)

            myDb = DatabaseHelper(this)


            AddData()
            getData()

            deleteData()
            viewAllData()


        }


        fun AddData() {

            btuadd.setOnClickListener(View.OnClickListener {

                val name = edit_name.text.toString().trim()
                val profession = edit_last.text.toString().trim()
                val salary = edit_number.text.toString().trim()


                if (TextUtils.isEmpty(name)) {
                    edit_name.error = "Enter name"
                    return@OnClickListener
                }

                if (TextUtils.isEmpty(profession)) {
                    edit_last.error = "Enter profession"
                    return@OnClickListener
                }
                if (TextUtils.isEmpty(salary)) {
                    edit_number.error = "Enter salary"
                    return@OnClickListener
                }

                val isInserted = myDb.intertData(name, profession, salary)

                if (isInserted == true) {
                    Toast.makeText(applicationContext, "Data inserted ", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Data could not be inserted ",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            })

        }

        fun viewAllData() {

            viewall.setOnClickListener(View.OnClickListener { view ->

                val res = myDb.getAllData()

                if (res.getCount() == 0) {
                    showMessage("Error ", "Nothing found")
                    return@OnClickListener

                } else {
                    val buffer = StringBuffer()
                    while (res.moveToNext()) {
                        buffer.append("Id:" + res.getString(0) + "\n")
                        buffer.append("Name: " + res.getString(1) + "\n\n")
                        buffer.append("Last_name: " + res.getString(2) + "\n\n")
                        buffer.append("Student_number: " + res.getString(3) + "\n\n")

                    }

                    showMessage("Data", buffer.toString())
                }
            })


        }


        fun deleteData() {
            btudelet.setOnClickListener(View.OnClickListener {
                val id =edit_id.getText().toString().trim()
                if (TextUtils.isEmpty(id)) {
                    edit_name.error = "Enter id"
                }

                val deleterows = myDb.daleteData(id)
                if (deleterows!! > 0) {
                    Toast.makeText(applicationContext, "Data deleted ", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(applicationContext, "Data could not deleted ", Toast.LENGTH_SHORT)
                        .show()

                }
            })

        }




//// ---- GET DATA


        fun getData() {
            viewall.setOnClickListener(View.OnClickListener {
                val id = edit_id.getText().toString().trim()

                if (TextUtils.isEmpty(id)) {
                    edit_id.setError("Enter ID")
                    return@OnClickListener
                }

                val res = myDb.getData(id)

                var data: String? = null

                if (res.moveToFirst()) {
                    data = "Id:" + res.getString(0) + "\n" +
                            "Name:" + res.getString(1) + "\n" +
                            "Last_name:" + res.getString(2) + "\n" +
                            "Student_number:" + res.getString(3) + "\n"
                }


                    showMessage("Data", data)
            })
        }



        fun showMessage(title: String, message: String?) {

            val builder = AlertDialog.Builder(this)
            builder.create()
            builder.setCancelable(true)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.show()
        }


    }
}
