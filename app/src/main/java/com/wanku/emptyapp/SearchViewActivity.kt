package com.wanku.emptyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.SearchView
import com.wanku.emptyapp.databinding.ActivitySearchViewBinding

class SearchViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchViewBinding
    var list = listOf<String>("Pan","Queso","Huevos","Pollo","Leche","Cebollas","Zanahorias","Pescado")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var searchView = binding.searchView
        var editTextMultiline = binding.editTextTextMultiLine2
        editTextMultiline.setText(listToString(list))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                allOp(p0,editTextMultiline)
                return false
            }

        })
    }

    fun listToString(list:List<String>):String{
        var string=""
        list.forEach {
            string=string+it+"\n"
        }
        return string
    }

    fun searchList(text:String?):List<String>{
        var filterList = list.filter { it.contains(text?:"",true) }
        return filterList
    }

    fun allOp(text: String?, multiLineText: EditText){
        var newList=searchList(text)
        var newString=listToString(newList)
        multiLineText.setText(newString)
    }
}