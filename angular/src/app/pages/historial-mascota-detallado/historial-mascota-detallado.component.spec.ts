import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorialMascotaDetalladoComponent } from './historial-mascota-detallado.component';

describe('HistorialMascotaDetalladoComponent', () => {
  let component: HistorialMascotaDetalladoComponent;
  let fixture: ComponentFixture<HistorialMascotaDetalladoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HistorialMascotaDetalladoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HistorialMascotaDetalladoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
