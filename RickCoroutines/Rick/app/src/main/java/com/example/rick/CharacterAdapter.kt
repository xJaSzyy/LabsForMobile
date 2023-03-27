import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rick.DataModel
import com.example.rick.R

class CharacterAdapter(
    private val list: List<DataModel.EpisodeResults>,
): RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.name)
        val airDate: TextView = itemView.findViewById(R.id.airDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.episode_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = list[position]

        holder.name.text = listItem.name
        holder.airDate.text = listItem.air_date
    }

    override fun getItemCount(): Int {
        return list.size
    }
}