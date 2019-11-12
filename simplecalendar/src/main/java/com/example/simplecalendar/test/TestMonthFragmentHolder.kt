package com.example.simplecalendar.test

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.simplecalendar.R
import com.example.simplecalendar.adapters.MyMonthPagerAdapter
import com.example.simplecalendar.extensions.config
import com.example.simplecalendar.fragments.MyFragmentHolder
import com.example.simplecalendar.helpers.DAY_CODE
import com.example.simplecalendar.helpers.Formatter
import com.example.simplecalendar.interfaces.NavigationListener
import com.simplemobiletools.commons.views.MyViewPager
import kotlinx.android.synthetic.main.fragment_months_holder.view.*
import org.joda.time.DateTime

class TestMonthFragmentHolder : MyFragmentHolder(), NavigationListener {

    private val PREFILLED_MONTHS = 251

    private var viewPager: MyViewPager? = null
    private var defaultMonthlyPage = 0
    private var todayDayCode = ""
    private var currentDayCode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentDayCode = arguments?.getString(DAY_CODE) ?: ""
        todayDayCode = Formatter.getTodayCode()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_months_holder, container, false);
        view.background = ColorDrawable(context!!.config.backgroundColor)
        viewPager = view.fragment_months_viewpager
        viewPager!!.id = (System.currentTimeMillis() % 100000).toInt()
        setupFragment()
        return view;
    }

    private fun setupFragment() {
        val codes = getMonth(currentDayCode)
        val monthlyAdapter = MyMonthPagerAdapter(activity!!.supportFragmentManager, codes, this)
        defaultMonthlyPage = codes.size / 2

        viewPager!!.apply {
            adapter = monthlyAdapter
            currentItem = defaultMonthlyPage
        }
    }

    private fun getMonth(code: String): List<String> {
        var month = ArrayList<String>(PREFILLED_MONTHS)
        var today = Formatter.getDateTimeFromCode(code).withDayOfMonth(1)
        for (i in -PREFILLED_MONTHS / 2..PREFILLED_MONTHS / 2) {
            month.add(Formatter.getDayCodeFromDateTime(today.plusMonths(i)))
        }
        return month
    }

    override fun goLeft() {
        viewPager!!.currentItem = viewPager!!.currentItem - 1
    }

    override fun goRight() {
        viewPager!!.currentItem = viewPager!!.currentItem + 1
    }

    override fun goToDateTime(dateTime: DateTime) {
        currentDayCode = Formatter.getDayCodeFromDateTime(dateTime)
        setupFragment()
    }

    override fun goToToday() {
        currentDayCode = todayDayCode
        setupFragment()
    }

    override fun showGoToDateDialog()   {
/*        activity!!.setTheme(context!!.getDialogTheme())
        val view = layoutInflater.inflate(R.layout.date_picker, null)
        val datePicker = view.findViewById<DatePicker>(R.id.date_picker)
        datePicker.findViewById<View>(Resources.getSystem().getIdentifier
        ("day", "id", "android")).beGone()

        val dateTime = DateTime(Formatter.getDateTimeFromCode(currentDayCode).toString())
        datePicker.init(dateTime.year, dateTime.monthOfYear -1, 1, null)
        AlertDialog.Builder(context!!)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.ok){ dialog, which ->  datePicked(dateTime, datePicker) }
                .create().apply { activity?.setupDialogStuff(view, this) }*/
    }

    override fun refreshEvents() {
        (viewPager?.adapter as? MyMonthPagerAdapter)?.updateCalendars(viewPager?.currentItem ?: 0)
    }

//    override fun shouldGoToTodayBeVisible() = currentDayCode.getMonthCode() != todayDayCode.getMonthCode()
//    override fun getNewEventDayCode() = if (shouldGoToTodayBeVisible()) currentDayCode else todayDayCode

    override fun updateActionBarTitle() {
//        (activity as? TestCalendarActivity)?.updateActionbarColor(R.string.app_launcher_name)
    }

    override fun shouldGoToTodayBeVisible(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNewEventDayCode(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}