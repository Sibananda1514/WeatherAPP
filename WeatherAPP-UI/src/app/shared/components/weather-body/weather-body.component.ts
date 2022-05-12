import { Component, Input, OnInit } from "@angular/core";

@Component({
  selector: 'app-weather-body',
  templateUrl: './weather-body.component.html',
  styleUrls: ['./weather-body.component.css']
})
export class WeatherBodyComponent implements OnInit {
  @Input('foreCastData')
  public forecast: any;
  @Input('currentData')
  public current: any;
  constructor() { }

  ngOnInit(): void {
  }

}
