package com.medivision.medivision.user.domain;

public class Paging {
    public static String pagingStr(int totalCount, int pageSize, int blockPage,
                                   int pageNum, String reqUrl) {
        String pagingStr = "";

        // 단계 1 : 전체 페이지 수 계산
        int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));
        System.out.println("totalPages: "+ totalPages);
        System.out.println("blockPage: "+ blockPage);
        System.out.println("pageNum: "+ pageNum);

        // 단계 2 : '이전 페이지 블록 바로가기' 출력
        int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
        System.out.println("pageTemp: "+ pageTemp);
        if (pageTemp != 1) {
            pagingStr += "<div><a class=page-block-button href='" + reqUrl + "?pageNum=1'>[첫 페이지]</a></div>";
            pagingStr += "<div><a class=page-block-button href='" + reqUrl + "?pageNum=" + (pageTemp - 1)
                    + "'>[이전 블록]</a></div> |";
        }

        // 단계 3 : 각 페이지 번호 출력
        int blockCount = 1;
        while (blockCount <= blockPage && pageTemp <= totalPages) {
            if (pageTemp == pageNum) {
                // 현재 페이지는 링크를 걸지 않음
                pagingStr += "<div class='page-button-div select-div'><a class='page-button selected-page'>"+pageTemp +"</a></div>";
            } else {
                pagingStr += "<div class='page-button-div'><a class='page-button option-page' href='" + reqUrl + "?pageNum=" + pageTemp
                        + "'>" + pageTemp + "</a></div>";
            }
            pageTemp++;
            blockCount++;
        }

        // 단계 4 : '다음 페이지 블록 바로가기' 출력
        if (pageTemp <= totalPages) {
            pagingStr += "| <div><a class=page-block-button href='" + reqUrl + "?pageNum=" + pageTemp
                    + "'>[다음 블록]</a></div>";
            pagingStr += "<div><a class=page-block-button href='" + reqUrl + "?pageNum=" + totalPages
                    + "'>[마지막 페이지]</a></div>";
        }

        return pagingStr;
    }
}
