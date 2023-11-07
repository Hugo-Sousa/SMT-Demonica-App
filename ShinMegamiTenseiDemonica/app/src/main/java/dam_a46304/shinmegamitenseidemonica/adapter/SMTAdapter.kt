package dam_a46304.shinmegamitenseidemonica.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dam_a46304.shinmegamitenseidemonica.activities.DemonDetail
import dam_a46304.shinmegamitenseidemonica.activities.FusionMain
import dam_a46304.shinmegamitenseidemonica.entities.Demon
import dam_a46304.shinmegamitenseidemonica.databinding.DemonItemBinding

class SMTAdapter (var context: Context) : ListAdapter<Demon, RecyclerView.ViewHolder>( PlantDiffCallback ()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DemonViewHolder (
                DemonItemBinding.inflate (
                        LayoutInflater.from ( parent.context ) ,
                        parent ,
                        false
                )
        ,context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val demon = getItem (position)
        (holder as DemonViewHolder).bind(demon)
    }

    class DemonViewHolder (
            private val binding : DemonItemBinding,
            val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener{
                if(context is FusionMain) {
                    binding.demon?.let { demon ->
                        context.setDemonToFuse(demon)
                        context.back(it)
                        context.getResult()
                    }
                }
                else{
                    binding.demon?.let { demon ->
                        val context: Context = it.context
                        val intent = Intent(context, DemonDetail::class.java)
                        intent.putExtra("demon", demon)
                        it.context.startActivity(intent)
                    }
                }
            }
        }
        fun bind ( item : Demon ) {
            binding.apply {
                demon = item
                executePendingBindings()
            }
        }
    }

}
private class PlantDiffCallback : DiffUtil.ItemCallback <Demon>() {
    override fun areItemsTheSame ( oldItem : Demon , newItem : Demon ): Boolean {
        return oldItem.name == newItem .name
    }
    override fun areContentsTheSame ( oldItem : Demon , newItem : Demon): Boolean {
        return oldItem == newItem
    }
}