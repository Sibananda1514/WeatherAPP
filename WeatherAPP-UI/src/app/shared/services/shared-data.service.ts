import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {
  LocationDataBehaviourResponse: BehaviorSubject<any> = new BehaviorSubject<any>({});
  WeatherDataServiceResponse: BehaviorSubject<any> = new BehaviorSubject<any>({});
  ForeCastDataBehaviourResponse: BehaviorSubject<any> = new BehaviorSubject<any>({});
  CurrentDataBehaviourResponse: BehaviorSubject<any> = new BehaviorSubject<any>({});
  WeatherResponseAddtionalData: BehaviorSubject<any> = new BehaviorSubject<any>({});
  AllLocationDataResponse: BehaviorSubject<any> = new BehaviorSubject<any>({});

  constructor() { }
}
