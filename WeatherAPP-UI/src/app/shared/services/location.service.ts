import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resouceconstant } from '../util/resouceconstant';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private API_END_POINT: string;
  constructor(private httpClient: HttpClient) {
    this.API_END_POINT = "http://sibananda:8082/";
    // this.API_END_POINT = Resouceconstant.API_END_POINT;
  }

  /** This is used for fetch max min temp of all avilable location*/
  public getAllAvilableLocationData(): Observable<any> {
    const url = this.API_END_POINT + Resouceconstant.GET_ALL_LOCATION;
    return this.httpClient.get(url, { observe: 'response' })
  };
  /**
   * This method used for create new location
   * @param location This is location user want to creat
   * @returns the response of create location api
   */
  public createloc(location: string): Observable<any> {
    const url = this.API_END_POINT + Resouceconstant.CREATE_LOCATION;
    let requestBody = {
      "flocationName": location
    };
    return this.httpClient.post(url, requestBody);
  }

  public updateLocationPosition(previousIndex: number, currentIndex: number): Observable<any> {
    const url = this.API_END_POINT + Resouceconstant.UPDATE_LOCATION_POSITION;
    let requestBody = {
      "currentIndex": currentIndex,
      "prevIndex": previousIndex
    };
    return this.httpClient.put(url, requestBody);
  }

  public deleteloc(locationId: string): Observable<any> {
    let url = this.API_END_POINT + Resouceconstant.DELETE_LOCATION + `${locationId}`;

    return this.httpClient.delete(url);
  }
}

