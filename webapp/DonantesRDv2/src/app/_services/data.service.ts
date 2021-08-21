import { Injectable } from '@angular/core';
import { Campaign } from '../_models/campaign.model';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  public val: number = 0;
  public campaign: Campaign | null = null;
  
}
