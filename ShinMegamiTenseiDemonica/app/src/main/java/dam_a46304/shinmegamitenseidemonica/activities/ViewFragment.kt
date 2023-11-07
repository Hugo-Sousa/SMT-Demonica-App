package dam_a46304.shinmegamitenseidemonica.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import dam_a46304.shinmegamitenseidemonica.R
import dam_a46304.shinmegamitenseidemonica.adapter.ViewAdapter


class ViewFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_view, container, false)

        val fragmentList = arrayListOf(
            demon_background(),
            demon_main_screen(),
            demon_skills()
        )

        val adapterPager = ViewAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)

        view.findViewById<ViewPager2>(R.id.viewPager).adapter = adapterPager
        view.findViewById<ViewPager2>(R.id.viewPager).currentItem = 1

        return view
    }


}