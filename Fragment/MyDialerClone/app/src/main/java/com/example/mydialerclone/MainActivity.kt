package com.example.mydialerclone

import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.google.gson.Gson
import okhttp3.*
import timber.log.Timber
import timber.log.Timber.Forest.plant
import java.util.*


class MainActivity : AppCompatActivity() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView: RecyclerView = findViewById(R.id.rView)
        recyclerView.addItemDecoration(VerticalSpaceItemDecoration(10))

        plant(Timber.DebugTree())

        buildRecyclerView(fetchList())

        val editText: EditText = findViewById(R.id.et_search)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                filter(editText.text.toString(), fetchList())
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun filter(text: String, list: List<Contact>) {
        val filteredList = list.filter { it.name.contains(text,true) or it.phone.contains(text, true) or it.type.contains(text,true) }
        buildRecyclerView(filteredList)
    }

    private fun fetchList(): List<Contact> {

        val contactList: List<Contact> = Gson().fromJson(myJson, Array<Contact>::class.java).toList()
        return contactList
    }

    data class Contact(
        val name: String,
        val phone: String,
        val type: String,
    )

    private fun buildRecyclerView(list: List<Contact>) {
        val recyclerView: RecyclerView = findViewById(R.id.rView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = Adapter(this, list as ArrayList<Contact>)


    }

    class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) :
        ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView,
            state: RecyclerView.State,
        ) {
            outRect.bottom = verticalSpaceHeight
        }
    }
}
