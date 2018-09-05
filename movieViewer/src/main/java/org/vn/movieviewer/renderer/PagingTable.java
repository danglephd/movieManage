/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.renderer;

import java.util.Iterator;
import java.util.List;
import org.vn.movieviewer.dao.daoMainMovie;
import org.vn.movieviewer.dto.MainMovie;

/**
 *
 * @author danglph
 */
public class PagingTable {

    /**
     * @param pageOffset the pageOffset to set
     */
    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    public static int FIRST_PAGE = 1;

    private int pageOffset = 16;
    private int totalPage;
    private int curentPage;
    private int start;
    private int limit;
    private int maxRow;
    private List<Integer> idList;
    protected List<Object> rowCache;

    public PagingTable(int totalRow, List<Object> rowCache, int pagOffset){
        this.curentPage = FIRST_PAGE;
        this.start = 0;
        this.maxRow = totalRow;
        this.pageOffset = pagOffset;
        this.totalPage = totalRow % this.pageOffset == 0 && totalRow > 0 ? totalRow / this.pageOffset : (totalRow / this.pageOffset) + 1;
        this.rowCache = rowCache;  
        this.limit = Math.min(this.curentPage * this.pageOffset, totalRow);      
    }
    
    public PagingTable(List<Integer> idList) {
        if (idList != null) {
            this.idList = idList;
            this.curentPage = FIRST_PAGE;
            this.maxRow = idList.size();
            this.totalPage = this.maxRow % this.pageOffset == 0 && !idList.isEmpty() ? this.maxRow / this.pageOffset : (this.maxRow / this.pageOffset) + 1;
            this.start = 0;
            this.limit = Math.min(this.curentPage * this.pageOffset, this.maxRow);
            AddPageToRowCache();
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        PagingTable pagingTable = new PagingTable(daoMainMovie.getIDList("")) {//new ArrayList<Integer>()
        
            @Override
            public boolean AddPageToRowCache() {
                List<MainMovie> mainMovies = daoMainMovie.get(false, "", "", this.getStart(), this.getLimit());
                this.rowCache.clear();
                for (Iterator<MainMovie> iterator = mainMovies.iterator(); iterator.hasNext();) {
                    MainMovie next = iterator.next();
                    this.rowCache.add(next);
                }
                return true;
            }
        };
    }
    
    public Object getByRow(int selectedRow){
        return rowCache.get(selectedRow);
    }

    
    public boolean AddPageToRowCache() {
        return true;
    }

    /**
     * @return the curentPage
     */
    public int getCurentPage() {
        return curentPage;
    }

    public void nextPage() {
        if (this.curentPage < this.getTotalPage()) {
            this.curentPage++;
            this.setStart((this.curentPage - 1) * this.getPageOffset());
            this.limit = Math.min(this.curentPage * this.getPageOffset(), this.maxRow);
            if(!AddPageToRowCache()){
                this.curentPage--;
                this.setStart((this.curentPage - 1) * this.getPageOffset());
                this.limit = Math.min(this.curentPage * this.getPageOffset(), this.maxRow);
            }
        }
    }

    public void prevPage() {
        if (this.curentPage > FIRST_PAGE) {
            curentPage--;
            this.setStart((this.curentPage - 1) * this.getPageOffset());
            this.limit = Math.min(this.curentPage * this.getPageOffset(), this.maxRow);
            AddPageToRowCache();
        }
    }

    /**
     * @param curentPage the curentPage to set
     */
    public void setCurentPage(int curentPage) {
        if (this.curentPage >= FIRST_PAGE && this.curentPage <= this.getTotalPage()) {
            this.curentPage = curentPage;
            this.setStart((this.curentPage - 1) * this.getPageOffset());
            this.limit = Math.min(this.curentPage * this.getPageOffset(), this.maxRow);
            AddPageToRowCache();
        }
    }

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @return the rowCache
     */
    public List<Object> getRowCache() {
        return rowCache;
    }

    /**
     * @return the totalPage
     */
    public int getTotalPage() {
        return totalPage;
    }
    
    public int getTotalRow(){
        return idList == null ? 0 : this.maxRow;
    }

    /**
     * @return the pageOffset
     */
    public int getPageOffset() {
        return pageOffset;
    }

    /**
     * @param start the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }
}
