package com.example.judes_darwinchandra

import com.example.judes_darwinchandra.Data.jobScheduleData

class jobInterviewMessage {
    // membuat var yang menampung arraylist<objdetailloker> yang diberi data
    var interviewList = arrayListOf<objDetailLoker>(objDetailLoker("PT. Indah Kiat Tbk", "Manager", "10 jt/Bulan", "Medan - Sumatera Utara","Interview : 15-May-20  (10:30 AM)","https://ceowatermandate.org/wp-content/uploads/2021/01/indah-kiat-logo.jpg"),
            objDetailLoker("PT. Bukit Asam Tbk", "CEO", "15 jt/Bulan", "Jakarta Utara","Interview : 19-May-20  (9:30 AM)","https://tambang.itm.ac.id/wp-content/uploads/2019/03/logo-pt-bukit-asam.jpg"),
            objDetailLoker("PT. Aneka Tambang Tbk", "IT Support", "5 jt/Bulan",
        "Bogor - Jawa Barat","Interview : 15-July-20  (8:30 AM)","https://www.dreamcareerbuilder.com/uploads/employers/5754_ECLB.png")
    )

    // membuat var inder -1 
    private var index = -1
    fun addMessage(obj :objDetailLoker) {
        interviewList.add(obj)
    }
    fun removeMessage(obj :objDetailLoker){
        interviewList.remove(obj)
    }
    // membuat fungsi kembali kestart jika index = -1
    fun backToStart() { index = -1}
    // membuat fungsi get message dari data objdetailloker
    fun getMessage() : objDetailLoker {
        // jika interview kosong maka mengembalikan hasil objdetail
        if(interviewList.size==0)
            return objDetailLoker("kosong","kosong","kosong","kosong","kosong","kosong")
        // jika index + 1 sama dengan size interviewlist maka jalan fungsi backtostart
        if(index+1==interviewList.size)
            backToStart()
        // index + 1
        index+=1
        // mengembalikan hasil dari index
        return interviewList.get(index)
    }
}