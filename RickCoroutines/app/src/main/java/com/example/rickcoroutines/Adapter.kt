package com.example.rickcoroutines

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import timber.log.Timber

class Adapter(private var items: MutableList<ItemType>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_CHARACTER_INFO = 1
    private val TYPE_LOAD_MORE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_CHARACTER_INFO -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_layout, parent, false)
                CharacterInfoViewHolder(view)
            }
            TYPE_LOAD_MORE -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.button_layout, parent, false)
                LoadMoreViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_CHARACTER_INFO -> (holder as CharacterInfoViewHolder).bind(holder.itemView.context, items[position] as ItemType.CharacterCard)
            TYPE_LOAD_MORE -> (holder as LoadMoreViewHolder).bind(items[position] as ItemType.ButtonObject)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ItemType.CharacterCard -> TYPE_CHARACTER_INFO
            is ItemType.ButtonObject -> TYPE_LOAD_MORE
        }
    }

    override fun getItemCount(): Int = items.size

    class CharacterInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, characterCard: ItemType.CharacterCard) {
            val image: ImageView = itemView.findViewById(R.id.image_character)
            val name: TextView = itemView.findViewById(R.id.txt_name)
            val gender: TextView = itemView.findViewById(R.id.txt_gender)

            Picasso.get().load(characterCard.image).into(image)
            name.text = characterCard.name
            gender.text = characterCard.gender
            Timber.tag("CHAR").d(characterCard.toString())

            itemView.setOnClickListener {
                val intent = Intent(context, CharacterActivity::class.java)
                intent.putExtra("episode", characterCard.episode)
                intent.putExtra("id", characterCard.id)
                context.startActivity(intent)
            }
        }
    }

    class LoadMoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(button: ItemType.ButtonObject) {
            val btn: Button = itemView.findViewById(R.id.button)
            btn.text = button.text
            btn.setOnClickListener {
                val model = ViewModelProvider(it.findViewTreeViewModelStoreOwner()!!)[ViewModel::class.java]
                Timber.tag("BUTTON").d("y")
                model.page += 1
                Timber.tag("Page").d(model.page.toString())
                model.getCharacterList()
            }
        }
    }

    fun submitList(newData: List<DataModel.CharacterResults>) {
        for (i in newData.indices) {
            val model = ItemType.CharacterCard(newData[i].id,
                newData[i].name,
                newData[i].status,
                newData[i].species,
                newData[i].type,
                newData[i].gender,
                newData[i].origin,
                newData[i].location,
                newData[i].image,
                newData[i].episode,
                newData[i].url,
                newData[i].created)
            items.add(model)
        }
    }
}