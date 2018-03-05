package com.example.demo.persistence;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalculatePercentage {
//    private List<ResultRowData> getResultList(Map<String, List<String>> classNameFAMap)
//    {
//        List<ResultRowData> resultList = new ArrayList<>();
//
//        for (Map.Entry<String, List<String>> entry : classNameFAMap.entrySet())
//        {
//            List<AvailabilityData> allFAList = new ArrayList<>();
//            for (Map.Entry<String, List<String>> faEntry : ListToolkit.toUniqueKeyMap(entry.getValue()).entrySet())
//            {
//                allFAList.add(new AvailabilityData(faEntry.getKey(),calculatePercentage(faEntry.getValue().size(), entry.getValue().size())));
//            }
//            resultList.add(new ResultRowData(entry.getKey(), allFAList));
//        }
//        return resultList;
//    }

    private double calculatePercentage(int itemCount, int totalCount)
    {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return Double.parseDouble(decimalFormat.format((double) itemCount * 100 / totalCount));

    }
}
