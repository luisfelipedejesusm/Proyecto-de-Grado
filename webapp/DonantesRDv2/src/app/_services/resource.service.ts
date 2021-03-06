import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Campaign } from '../_models/campaign.model';
import { DateAndTime } from '../_models/date-and-time.model';
import { DonationCenter } from '../_models/donation-center.model';
import { User } from '../_models/user.model';
import { SharedService } from './shared.service';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {
  constructor(private http: HttpClient, private shared: SharedService) { }

  getBloodGroups(): String[] {
    return ["A_NEGATIVE", "A_POSITIVE", "B_NEGATIVE", "B_POSITIVE", "O_NEGATIVE", "O_POSITIVE", "AB_NEGATIVE", "AB_POSITIVE"];
  }

  getDonationCenters(): Observable<DonationCenter[]>{
    return this.http.get<DonationCenter[]>(this.shared.getApiUrl() + 'public/donation-center');
  }

  getDonationCenterAppointmentDates(donationCenterId: Number): Observable<DateAndTime[]>{
    return this.http.get<DateAndTime[]>(this.shared.getApiUrl() + 'public/donation/appointment-dates/' + donationCenterId);
  }

  getPublicDonors(): Observable<User[]>{
    return this.http.get<User[]>(this.shared.getApiUrl() + 'public/donors');
  }

  getWorkingHours(start: number, end: number): String[]{
    let workingHours: String[] = [];
    for(let i = start; i < end; i++){
      workingHours.push(`${i < 10? "0" + i: i}:00`);
      workingHours.push(`${i < 10? "0" + i: i}:30`);
    }
    return workingHours;
  }

  getLastCampaigns(): Observable<Campaign[]>{
    return this.http.get<Campaign[]>(this.shared.getApiUrl() + "public/lastCampaigns");
  }
}
