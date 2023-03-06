package com.example.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragment.databinding.FragmentContactBinding
import com.google.gson.Gson


class ContactFragment : Fragment() {
    private val myJson = "[\n" +
            "  {\n" +
            "    \"name\": \"(Приёмная)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-80\",\n" +
            "    \"type\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Бухгалтерия)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-64\",\n" +
            "    \"type\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Бухгалтерия)\",\n" +
            "    \"phone\": \"+375 (2239) 7-18-08\",\n" +
            "    \"type\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Юридическое бюро)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-63\",\n" +
            "    \"type\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Отдел правовой и кадровой работы)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-93\",\n" +
            "    \"type\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Отдел материально-технического снабжения)\",\n" +
            "    \"phone\": \"+375 (2239) 7-18-12\",\n" +
            "    \"type\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"\",\n" +
            "    \"phone\": \"+375 44 712 36 26\",\n" +
            "    \"type\": \"Сектор сбыта бумаги\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Реализация на внутренний рынок)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-79\",\n" +
            "    \"type\": \"Сектор сбыта бумаги\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Реализация на внешний рынок)\",\n" +
            "    \"phone\": \"+375 (2239) 4-11-77\",\n" +
            "    \"type\": \"Сектор сбыта бумаги\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Реализация на внутренний рынок)\",\n" +
            "    \"phone\": \"+375 (2239) 7-18-25\",\n" +
            "    \"type\": \"Сектор сбыта бумаги\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"\",\n" +
            "    \"phone\": \"+375 44 580 09 70\",\n" +
            "    \"type\": \"Сектор сбыта продукции деревообработки\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Реализация продукции деревообработки)\",\n" +
            "    \"phone\": \"+375 (2239) 7-18-42\",\n" +
            "    \"type\": \"Сектор сбыта продукции деревообработки\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Реализация продукции деревообработки)\",\n" +
            "    \"phone\": \"+375 (2239) 3-64-71\",\n" +
            "    \"type\": \"Сектор сбыта продукции деревообработки\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"\",\n" +
            "    \"phone\": \"+375 29 352 25 20\",\n" +
            "    \"type\": \"Реализация домов, бань, беседок, клеёного бруса\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"\",\n" +
            "    \"phone\": \"+375 (2239) 7-18-43\",\n" +
            "    \"type\": \"Реализация домов, бань, беседок, клеёного бруса\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(приемная)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-80\",\n" +
            "    \"type\": \"Факс\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(отдел сбыта)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-79\",\n" +
            "    \"type\": \"Факс\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(отдел материально-технического снабжения)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-82\",\n" +
            "    \"type\": \"Факс\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(служба главного энергетика)\",\n" +
            "    \"phone\": \"+375 (2239) 7-18-06\",\n" +
            "    \"type\": \"Факс\"\n" +
            "  }\n" +
            "]"


    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentContactBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.rView)
        recyclerView.addItemDecoration(MainActivity.VerticalSpaceItemDecoration(10))
        buildRecyclerView(fetchList(), view)

        dataModel.message.observe(viewLifecycleOwner) {
            filter(it, fetchList(), view)
        }
    }

    fun buildRecyclerView(list: List<MainActivity.Contact>, view: View) {

        val recyclerView: RecyclerView = view.findViewById(R.id.rView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = Adapter(list as ArrayList<MainActivity.Contact>)
    }

    fun fetchList(): List<MainActivity.Contact> {
        val contactList: List<MainActivity.Contact> = Gson().fromJson(myJson, Array<MainActivity.Contact>::class.java).toList()
        return contactList
    }

    fun filter(text: String, list: List<MainActivity.Contact> = fetchList(), view: View) {
        val filteredList = list.filter { it.name.contains(text,true) or it.phone.contains(text, true) or it.type.contains(text,true) }
        buildRecyclerView(filteredList, view)
    }
}