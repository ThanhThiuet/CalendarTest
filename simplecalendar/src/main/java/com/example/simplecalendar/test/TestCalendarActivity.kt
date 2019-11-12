package com.example.simplecalendar.test

import android.os.Bundle
import com.example.simplecalendar.R
import com.example.simplecalendar.activities.SimpleActivity
import com.example.simplecalendar.extensions.config
import com.example.simplecalendar.extensions.seconds
import com.example.simplecalendar.fragments.MyFragmentHolder
import com.example.simplecalendar.fragments.WeekFragmentsHolder
import com.example.simplecalendar.helpers.*
import com.simplemobiletools.commons.extensions.hasPermission
import com.simplemobiletools.commons.helpers.PERMISSION_READ_CALENDAR
import com.simplemobiletools.commons.helpers.PERMISSION_WRITE_CALENDAR
import org.joda.time.DateTime

class TestCalendarActivity : SimpleActivity() {

    private var currentFragments = ArrayList<MyFragmentHolder>()

    private var mStoredTextColor = 0
    private var mStoredBackgroundColor = 0
    private var mStoredPrimaryColor = 0
    private var mStoredDayCode = ""
    private var mStoredIsSundayFirst = false
    private var mStoredUse24HourFormat = false
    private var mStoredDimPastEvents = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_calendar)

        storeStateVariables()
        if (!hasPermission(PERMISSION_WRITE_CALENDAR) || !hasPermission(PERMISSION_READ_CALENDAR)) {
            config.caldavSync = false
        }
        updateViewPager()
    }

    private fun storeStateVariables() {
        config.apply {
            mStoredIsSundayFirst = isSundayFirst
            mStoredTextColor = textColor
            mStoredPrimaryColor = primaryColor
            mStoredBackgroundColor = backgroundColor
            mStoredUse24HourFormat = use24HourFormat
            mStoredDimPastEvents = dimPastEvents
        }
        mStoredDayCode = Formatter.getTodayCode()
    }

    private fun updateViewPager(dayCode: String? = Formatter.getTodayCode()) {
        val fragment = getFragmentsHolder()
        currentFragments.forEach {
            supportFragmentManager.beginTransaction().remove(it).commitNow()
        }
        currentFragments.clear()
        currentFragments.add(fragment)
        val bundle = Bundle()

        when (config.storedView) {
            DAILY_VIEW, MONTHLY_VIEW -> bundle.putString(DAY_CODE, dayCode)
            WEEKLY_VIEW -> bundle.putString(WEEK_START_DATE_TIME, getThisWeekDateTime())
        }
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.fragments_holder, fragment).commitNow()
    }

    private fun getThisWeekDateTime(): String {
        var thisweek = DateTime().withDayOfWeek(1).withTimeAtStartOfDay()
                .minusDays(if (config.isSundayFirst) 1 else 0)
        if (DateTime().minusDays(7).seconds() > thisweek.seconds()) {
            thisweek = thisweek.plusDays(7)
        }
        return thisweek.toString()
    }

    private fun getFragmentsHolder() = when (config.storedView) {
        MONTHLY_VIEW -> TestMonthFragmentHolder()
        else -> TestMonthFragmentHolder()
    }
}
