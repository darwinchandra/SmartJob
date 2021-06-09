package com.example.judes_darwinchandra

import com.example.judes_darwinchandra.Data.jobScheduleData

class jobInterviewMessage {
    // membuat var yang menampung arraylist<objdetailloker> yang diberi data
    var interviewList = arrayListOf<objDetailLoker>(objDetailLoker("PT. Indah Kiat Tbk", "Financial Manager", "10 jt/Bulan", "Medan - Sumatera Utara","15-May-21  (10:30 AM)","https://ceowatermandate.org/wp-content/uploads/2021/01/indah-kiat-logo.jpg"),
            objDetailLoker("PT. Bukit Asam Tbk", "CEO", "15 jt/Bulan", "Jakarta Utara","19-May-21  (9:30 AM)","https://tambang.itm.ac.id/wp-content/uploads/2019/03/logo-pt-bukit-asam.jpg"),
            objDetailLoker("PT. Aneka Tambang Tbk", "IT Support", "5 jt/Bulan",
        "Bogor - Jawa Barat","15-July-21  (8:30 AM)","https://www.dreamcareerbuilder.com/uploads/employers/5754_ECLB.png")
    )

    // membuat index menjadi 0
    private var index = 0
    fun addMessage(obj :objDetailLoker) {
        interviewList.add(obj)
    }
    fun removeMessage(obj :objDetailLoker){
        interviewList.remove(obj)
    }
    // membuat fungsi get message dari data objdetailloker
    fun getMessage() : objDetailLoker {
        // jika interview kosong maka mengembalikan hasil objdetail
        if(interviewList.size==0)
            return objDetailLoker("kosong","kosong","kosong","kosong","kosong","kosong")
        // mengembalikan hasil dari index yaitu data ke 0(pertama) saja
        return interviewList.get(index)
    }
}