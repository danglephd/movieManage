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

    public static int FIRST_PAGE = 1;

    private int pageOffset = 16;
    private int totalPage;
    private int curentPage;
    private int start;
    private int limit;
    private List<Integer> idList;
    protected List<Object> rowCache;

    public PagingTable(List<Integer> idList) {
        if (idList != null) {
            this.idList = idList;
            this.curentPage = FIRST_PAGE;
            this.totalPage = idList.size() % this.pageOffset == 0 && !idList.isEmpty() ? idList.size() / this.pageOffset : (idList.size() / this.pageOffset) + 1;
            this.start = 0;
            this.limit = Math.min(this.curentPage * this.pageOffset, idList.size());
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
            public void AddPageToRowCache() {
                List<MainMovie> mainMovies = daoMainMovie.get(false, "", "", this.getStart(), this.getLimit());
                this.rowCache.clear();
                for (Iterator<MainMovie> iterator = mainMovies.iterator(); iterator.hasNext();) {
                    MainMovie next = iterator.next();
                    this.rowCache.add(next);
                }
            }
        };
    }
    
    public Object getByRow(int selectedRow){
        return rowCache.get(selectedRow);
    }

    
    public void AddPageToRowCache() {
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
            this.limit = Math.min(this.curentPage * this.getPageOffset(), idList.size());
            AddPageToRowCache();
        }
    }

    public void prevPage() {
        if (this.curentPage > FIRST_PAGE) {
            curentPage--;
            this.setStart((this.curentPage - 1) * this.getPageOffset());
            this.limit = Math.min(this.curentPage * this.getPageOffset(), idList.size());
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
            this.limit = Math.min(this.curentPage * this.getPageOffset(), idList.size());
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
        return idList == null ? 0 : idList.size();
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
