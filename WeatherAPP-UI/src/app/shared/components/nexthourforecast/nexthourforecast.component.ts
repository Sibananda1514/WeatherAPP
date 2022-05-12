import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-nexthourforecast',
  templateUrl: './nexthourforecast.component.html',
  styleUrls: ['./nexthourforecast.component.css']
})
export class NexthourforecastComponent implements OnInit {
  @Input('addtionalInfo')
  public addtionalInfo: any;
  @Input('currentData')
  public current: any;

  constructor() { }

  ngOnInit(): void {
  }

}
