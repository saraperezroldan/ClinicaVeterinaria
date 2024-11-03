import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionInfoMascotasComponent } from './gestion-info-mascotas.component';

describe('GestionInfoMascotasComponent', () => {
  let component: GestionInfoMascotasComponent;
  let fixture: ComponentFixture<GestionInfoMascotasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GestionInfoMascotasComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GestionInfoMascotasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
