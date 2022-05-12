import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-weather-header',
  templateUrl: './weather-header.component.html',
  styleUrls: ['./weather-header.component.css']
})
export class WeatherHeaderComponent implements OnInit {
  @Input('currentData')
  public current: any;

  @Input('locationData')
  public location: any;
  @Input('forcastData')
  public forecast: any;
  constructor() { }

  ngOnInit(): void {
  }

}
