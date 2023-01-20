package com.example.ddoing

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ddoing.R
import com.example.ddoing.databinding.FragmentHomeBinding

/**
 * 홈페이지
 */

class HomeFragment : Fragment() {
    // binding 객체로 뷰들 조정함
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.btm_nav_menu, menu)

        val calenderButton = menu.findItem(R.id.switch_calender)
        val socialButton = menu.findItem(R.id.switch_social)
    }

    private fun chooseLayout() {

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = TodaysDateAdapter()

    }

    override fun onViewCreated(view: View, savedInstaceState: Bundle?) {
        recyclerView = binding.recyclerView
        chooseLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}