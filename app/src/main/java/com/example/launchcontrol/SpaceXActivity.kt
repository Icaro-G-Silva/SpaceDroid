package com.example.launchcontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.launchcontrol.enums.SpaceXEnum
import com.example.launchcontrol.recyclerviews.RecyclerViewSpaceXAdapter
import com.example.launchcontrol.retrofit.entities.Launches
import com.example.launchcontrol.retrofit.RetroFitConfig
import com.example.launchcontrol.utils.Dialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SpaceXActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView
    private lateinit var loading: ProgressBar

    private lateinit var launchYear: EditText
    private lateinit var launchYearContent: String

    private val date: Date = Calendar.getInstance().time
    private val actualYear: String = SimpleDateFormat("yyyy", Locale.getDefault()).format(date)

    private val launches: MutableList<Launches> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_x)

        launchYear = findViewById(R.id.launchYear)
        loading = findViewById(R.id.loading)
        recycler = findViewById(R.id.recyclerViewMenu)

        generateLaunchYearListener()
    }

    private fun renderRecycler() {
        recycler.adapter = RecyclerViewSpaceXAdapter(launches)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.setHasFixedSize(false)
    }

    private fun generateLaunchYearListener() {
        launchYear.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val launchYearText: String = launchYear.text.toString()
                if(launchYearText.isNotEmpty() && launchYearText.length == 4) {
                    if(launchYearText.toInt() >= SpaceXEnum.INITIAL_YEAR.year && launchYearText.toInt() < actualYear.toInt()) {
                        launchYearContent = launchYearText
                        getAPIinfo()
                    } else Dialog("We don't have any launches' information for this year\n\nD:", "Mismatch year", this@SpaceXActivity).show()
                }
            }
        })
    }

    private fun getAPIinfo() {
        loading.visibility = View.VISIBLE
        recycler.visibility = View.GONE
        val call: Call<List<Launches>> = RetroFitConfig().getLaunchesService().getLaunches(launchYearContent)
        call.enqueue(object: Callback<List<Launches>> {
            override fun onResponse(call: Call<List<Launches>>?, response: Response<List<Launches>>?) {
                if(response?.isSuccessful!!) {
                    loading.visibility = View.GONE
                    recycler.visibility = View.VISIBLE
                    launches.clear()
                    response.body().forEach { launch -> launches.add(launch) }
                    renderRecycler()
                }
            }

            override fun onFailure(call: Call<List<Launches>>?, t: Throwable?) {
                loading.visibility = View.GONE
                Dialog("You may try again later\n\n:)", "API Error", this@SpaceXActivity).show()
                Log.e(null, t.toString())
            }
        })
    }

}