package com.example.restapiassignment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private val apiService = APIService.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_main, container, false)
        initRecyclerView(view)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun initRecyclerView(view: View) {


        val post = apiService.getPosts()
        Log.i("FirstFragment", "buttonFirst")
        post.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val postList = response.body()

                val recyclerview = view.findViewById<RecyclerView>(R.id.rv_post)
                recyclerview.layoutManager = LinearLayoutManager(activity)

                recyclerview.adapter = PostAdapter(postList!!,view)
//
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }

        })

    }


    fun getItems():ArrayList<Post>{
        val items =  ArrayList<Post>()

        items.add(Post("University of Kelaniya",6,"UOK",1))
        items.add(Post("University of Colombo",6,"UOC",1))

        return items
    }
}