package com.example.fragment.subcontechs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();

        List<String> m1a = new ArrayList<String>();
        m1a.add("Yenikapı (M2 ve Marmaray'a geçiş) ");
        m1a.add("Aksaray (T1'e geçiş) ");
        m1a.add("Emniyet ");
        m1a.add("Ulubatlı (T4'e geçiş) ");
        m1a.add("Bayrampaşa ");
        m1a.add("Sağmalcılar ");
        m1a.add("Kocatepe ");
        m1a.add("Otogar (M1B'ye geçiş) ");
        m1a.add("Terazidere");
        m1a.add("Davutpaşa ");
        m1a.add("Merter (metrobüse geçiş) ");
        m1a.add("Zeytinburnu (metrobüse geçiş) ");
        m1a.add("İncirli ");
        m1a.add("Bahçelievler ");
        m1a.add("Ataköy (metrobüse geçiş) ");
        m1a.add("Yenibosna ");
        m1a.add("DTM Fuar Merkezi ");
        m1a.add("Atatürk Havalimanı ");

        List<String> m1b = new ArrayList<String>();
        List<String> m2 = new ArrayList<String>();
        List<String> m3 = new ArrayList<String>();
        List<String> m4 = new ArrayList<String>();
        List<String> m5 = new ArrayList<String>();
        List<String> m6 = new ArrayList<String>();
        List<String> m7 = new ArrayList<String>();

        expandableListDetail.put("Yenikapı - Otogar - Atatürk Havalimanı", m1a);
        expandableListDetail.put("Yenikapı - Otogar - Halkalı Gar", m1b);
        expandableListDetail.put("Yenikapı - Hacıosman", m2);
        expandableListDetail.put("Kayaşehir - Bakırköy", m3);
        expandableListDetail.put("Kadıköy - Tuzla", m4);
        expandableListDetail.put("Üsküdar - Sabiha Gökçen Havalimanı", m5);
        expandableListDetail.put("Levent - Rumeli Hisarüstü", m6);
        expandableListDetail.put("Bahçeşehir - Kabataş", m7);

        return expandableListDetail;
    }
}