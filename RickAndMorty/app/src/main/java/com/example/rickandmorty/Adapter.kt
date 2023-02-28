import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.CharacterActivity
import com.example.rickandmorty.DataModel
import com.example.rickandmorty.R
import com.squareup.picasso.Picasso

class Adapter(
    private val context: Context,
    private val characterList: List<DataModel.CharacterResults>,
): RecyclerView.Adapter<Adapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.image_character)
        val txt_name: TextView = itemView.findViewById(R.id.txt_name)
        val txt_gender: TextView = itemView.findViewById(R.id.txt_gender)

        fun bind(context: Context, id: Int) {
            itemView.setOnClickListener {
                val intent = Intent(context, CharacterActivity::class.java)
                //intent.putExtra("episode", episode)
                intent.putExtra("id", id)
                context.startActivity(intent)
            }
            txt_name.setOnClickListener {
                val intent = Intent(context, CharacterActivity::class.java)
                intent.putExtra("id", id)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = characterList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = characterList[position]
        holder.bind(context, listItem.id!!)

        Picasso.get().load(listItem.image).into(holder.image)
        holder.txt_name.text = listItem.name
        holder.txt_gender.text = listItem.gender
    }

}