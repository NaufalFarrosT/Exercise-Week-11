package com.example.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.advweek4.R
import com.example.advweek4.util.loadImage
import com.example.advweek4.viewmodel.DetailViewModel
import com.example.advweek4.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel:DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null)
        {
            val student_ID=StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID

            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(student_ID)
        }

        observe()
    }

    fun observe() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            if(it != null)
            {
                imgDetail.loadImage(it.photoUrl, progressBarStudentDetail)
                txtDetailID.setText(it.id)
                txtDetailName.setText(it.name)
                txtDetailBod.setText(it.bod)
                txtDetailPhone.setText(it.phone)

                var student = it
                btnNotif.setOnClickListener{
                    Observable.timer(5, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe{
                            Log.d("Messages", "Five Seconds")
                            MainActivity.showNotification(student.name.toString(), "A new notification created", R.drawable.ic_baseline_person_24)
                        }
                }
            }
        })
    }
}