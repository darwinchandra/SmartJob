package com.example.judes_darwinchandra

import com.example.judes_darwinchandra.Data.jobScheduleData

class jobInterviewMessage {
    var interviewList = arrayListOf<objDetailLoker>(objDetailLoker("PT. Indah Kiat Tbk", "Manager", "10 jt/Bulan", "Medan - Sumatera Utara","Interview : 15-May-20  (10:30 AM)","https://ceowatermandate.org/wp-content/uploads/2021/01/indah-kiat-logo.jpg"),
            objDetailLoker("PT. Bukit Asam Tbk", "CEO", "15 jt/Bulan", "Jakarta Utara","Interview : 19-May-20  (9:30 AM)","https://tambang.itm.ac.id/wp-content/uploads/2019/03/logo-pt-bukit-asam.jpg"),
            objDetailLoker("PT. Aneka Tambang Tbk", "IT Support", "5 jt/Bulan",
        "Bogor - Jawa Barat","Interview : 15-July-20  (8:30 AM)","https://www.dreamcareerbuilder.com/uploads/employers/5754_ECLB.png")
    )

    private var index = -1
    fun addMessage(obj :objDetailLoker) {
        interviewList.add(obj)
    }
    fun removeMessage(obj :objDetailLoker){
        interviewList.remove(obj)
    }
    fun backToStart() { index = -1}
    fun getMessage() : objDetailLoker {
        if(interviewList.size==0)
            return objDetailLoker("kosong","kosong","kosong","kosong","kosong","kosong")
        if(index+1==interviewList.size)
            backToStart()
        index+=1
        return interviewList.get(index)
    }
}