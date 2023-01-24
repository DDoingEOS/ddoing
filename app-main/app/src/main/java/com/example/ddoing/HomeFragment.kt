package com.example.ddoing

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ddoing.R
import com.example.ddoing.databinding.FragmentHomeBinding
import com.example.ddoing.planAdd.PlanAdd

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

    private fun chooseLayout() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = TodaysDateAdapter()

    }

    override fun onViewCreated(view: View, savedInstaceState: Bundle?) {
        recyclerView = binding.homeviewTodaysDate
        chooseLayout()
        // 계획추가 버튼
        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(context, PlanAdd::class.java)
            context?.startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}