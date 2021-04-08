package com.example.launchcontrol.spacex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.launchcontrol.R
import com.example.launchcontrol.lists.recyclerviews.spacex.RecyclerViewSpaceXAdapter
import com.example.launchcontrol.retrofit.entities.Launches
import com.example.launchcontrol.spacex.presenter.ISpaceXContract
import com.example.launchcontrol.spacex.presenter.SpaceXPresenter
import com.example.launchcontrol.utils.Dialog

class SpaceXActivity : AppCompatActivity(), ISpaceXContract.ISpaceXActivity {
    private lateinit var recycler: RecyclerView
    private lateinit var loading: ProgressBar
    private lateinit var launchYear: EditText

    private var presenter: ISpaceXContract.ISpaceXPresenter? = SpaceXPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_space_x)

        launchYear = findViewById(R.id.launchYear)
        loading = findViewById(R.id.loading)
        recycler = findViewById(R.id.recyclerViewMenu)

        generateLaunchYearListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter = null
    }

    private fun generateLaunchYearListener() {
        launchYear.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val launchYearText: String = launchYear.text.toString()
                presenter?.launchYearListener(launchYearText)
            }
        })
    }

    override fun renderRecycler(launches: List<Launches>) {
        recycler.adapter = RecyclerViewSpaceXAdapter(launches)
        recycler.setHasFixedSize(false)
    }

    override fun setContentVisible() {
        loading.visibility = View.GONE
        recycler.visibility = View.VISIBLE
    }

    override fun setContentInvisible() {
        loading.visibility = View.VISIBLE
        recycler.visibility = View.GONE
    }

    override fun setLoadingInvisible() {
        loading.visibility = View.GONE
    }

    override fun showDialog(message: Int, title: Int) {
        Dialog(getString(message), getString(title), this@SpaceXActivity).show()
    }

}