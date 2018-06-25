
package ru.tsystems.trainscheduleboard;

/**
 *
 * @author ANovov
 */
public interface RestClient {
 
    String askService(String dateFrom, String dateTo);
    String init();
}
