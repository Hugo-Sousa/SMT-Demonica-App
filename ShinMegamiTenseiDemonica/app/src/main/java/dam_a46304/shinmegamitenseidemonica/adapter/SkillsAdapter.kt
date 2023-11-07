package dam_a46304.shinmegamitenseidemonica.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dam_a46304.shinmegamitenseidemonica.activities.DemonDetail
import dam_a46304.shinmegamitenseidemonica.activities.FusionMain
import dam_a46304.shinmegamitenseidemonica.entities.Demon
import dam_a46304.shinmegamitenseidemonica.databinding.SkillItemBinding
import dam_a46304.shinmegamitenseidemonica.entities.Skill

class SkillsAdapter (var context: Context, var initTransf: Boolean) : ListAdapter<Skill, RecyclerView.ViewHolder>( SkillsDiffCallback ()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SkillViewHolder (
                SkillItemBinding.inflate (
                        LayoutInflater.from ( parent.context ) ,
                        parent ,
                        false
                )
        ,context, initTransf)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val skill = getItem (position)
        (holder as SkillViewHolder).bind(skill)
    }

    class SkillViewHolder (
            private val binding : SkillItemBinding,
            val context: Context, initTransf: Boolean
    ) : RecyclerView.ViewHolder(binding.root) {

        var wasTransf = initTransf

        init {
            binding.setClickListener {
                if (context is FusionMain) {
                    if (!wasTransf) {
                        binding.skill?.let { skill ->
                            wasTransf = context.transferSkill(skill.name)
                        }
                    }
                    else
                        binding.skill?.let { skill ->
                            context.detransferSkill(skill.name)
                            wasTransf = false
                    }
                }
            }
        }

        fun bind ( item : Skill ) {
            binding.apply {
                skill = item
                executePendingBindings()
            }
        }
    }

}
private class SkillsDiffCallback : DiffUtil.ItemCallback <Skill>() {
    override fun areItemsTheSame ( oldItem : Skill , newItem : Skill ): Boolean {
        return oldItem.name == newItem .name
    }
    override fun areContentsTheSame ( oldItem : Skill , newItem : Skill): Boolean {
        return oldItem == newItem
    }
}