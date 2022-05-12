import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, OnInit } from '@angular/core';
import { SharedDataService } from '../../services/shared-data.service';
import { WeatherService } from '../../services/weather.service';
@Component({
  selector: 'app-weather-home',
  templateUrl: './weather-home.component.html',
  styleUrls: ['./weather-home.component.css']
})
export class WeatherHomeComponent implements OnInit {

  constructor(private shrdDtSer: SharedDataService, private weatherSer: WeatherService) { }
  public weatherDataRes: any;
  public forecast: any;
  public current: any;
  public addtionalInfo: any;
  public location: any;
  private currentCity: string = 'INDIA';
  public isShow: boolean = true;


  ngOnInit(): void {
    this.loadCityData(this.currentCity);
    this.shrdDtSer.ForeCastDataBehaviourResponse.subscribe((resp) => {
      this.forecast = resp;
    })
    this.shrdDtSer.CurrentDataBehaviourResponse.subscribe((resp) => {
      this.current = resp;
    });
    this.shrdDtSer.WeatherResponseAddtionalData.subscribe((resp) => {
      this.addtionalInfo = resp;
    });
    this.shrdDtSer.LocationDataBehaviourResponse.subscribe((resp) => {
      this.location = resp;
    });
  }
  loadCityData(cityName: string) {
    this.weatherSer.getWeatheDataBasedOnLocation(cityName).subscribe((resp) => {
      this.shrdDtSer.WeatherDataServiceResponse.next(resp);
      this.shrdDtSer.WeatherDataServiceResponse.subscribe(resp => {
        this.shrdDtSer.CurrentDataBehaviourResponse.next(resp.current);
        this.shrdDtSer.ForeCastDataBehaviourResponse.next(resp.forecast);
        this.shrdDtSer.LocationDataBehaviourResponse.next(resp.location);
        this.shrdDtSer.WeatherResponseAddtionalData.next(resp.addtionalInfo);
        this.weatherDataRes = resp;
      });
    });
  }
  updateCurrentCity($event: any) {
    const newCityName = $event;
    if (newCityName !== this.currentCity) {
      this.currentCity = $event;
      this.loadCityData(this.currentCity);
    }
  }

  toggelSideMenu($event: any) {
    this.isShow=$event;
  }
}
