import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-weather-footer',
  templateUrl: './weather-footer.component.html',
  styleUrls: ['./weather-footer.component.css']
})
export class WeatherFooterComponent implements OnInit {

  @Input('foreCastData')
  public forecast: any;
  @Input('currentData')
  public current: any;
  @Output('toggelSearchOption')
  private showSearchOptionEmiter = new EventEmitter<boolean>();
  private isHide: boolean = true;

  constructor() { }

  ngOnInit(): void {
  }

  toggelSearchOption() {
    this.isHide ? this.isHide = false : this.isHide = true;
    this.showSearchOptionEmiter.emit(this.isHide);
  }
}
