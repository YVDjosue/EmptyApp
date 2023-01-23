package com.wanku.emptyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import com.wanku.emptyapp.databinding.ActivityLayoutsBinding

class LayoutsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLayoutsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLayoutsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gridLayout = binding.gridLayout
        gridLayout.columnCount=2
        gridLayout.orientation=GridLayout.VERTICAL
    }
}