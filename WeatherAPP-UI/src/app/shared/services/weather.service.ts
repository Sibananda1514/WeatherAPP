import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resouceconstant } from '../util/resouceconstant';
@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  private API_END_POINT: string;
  constructor(private httpClient: HttpClient) {
    this.API_END_POINT = Resouceconstant.API_END_POINT;
  }

  public getWeatheDataBasedOnLocation(location: string): Observable<any> {
    const url = this.API_END_POINT + Resouceconstant.GET_WEATHER_DATA;
    return this.httpClient.get(url, {
      params: {
        location: location
      }
    });
  }

}
