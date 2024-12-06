import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProximosTratamientosComponent } from './proximos-tratamientos.component';

describe('ProximosTratamientosComponent', () => {
  let component: ProximosTratamientosComponent;
  let fixture: ComponentFixture<ProximosTratamientosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProximosTratamientosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProximosTratamientosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
