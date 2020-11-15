package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "SRE_FAC_SOLICITUDES_CERTIFICACIONES", schema = "SRE_RECAUDACIONES")
public class SreSistemasCertificacionEstado  implements Serializable {

	   //NMV
		private static final long serialVersionUID = 1L;

		@Id	
		@Column(name = "SOLICITUD_CERTIFICACION_ID")
		private long solicitudCertificacionId;

		@Column(name = "PERSONA_CONTRIBUYENTE_ID")
		private long personaContribuyenteId;

		@Column(name = "ESTADO_SOLICITUD_CERTIFICACION_ID")
		private int estadoSolicitudCertificacionId;
		
		@Column(name = "FECHA_APROBACION")
		private Date fechaAprobacion;
		
		@Column(name = "SISTEMA_ID")
		private Long sistemaId;
		
		//Columnas de Sistemas
		
		@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
		@JoinColumn(name = "NOMBRE_SISTEMA")	
		private SreSistemas nombreSistema;
		
		@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	    @JoinColumn(name = "VERSION")
		private SreSistemas version;
		
		
		
		
		public SreSistemasCertificacionEstado(long solicitudCertificacionId, long personaContribuyenteId,
			 int estadoSolicitudCertificacionId, Date fechaAprobacion,Long sistemaId,SreSistemas nombreSistema,SreSistemas version) {
			this.solicitudCertificacionId = solicitudCertificacionId;		
			this.personaContribuyenteId = personaContribuyenteId;			
			this.estadoSolicitudCertificacionId = estadoSolicitudCertificacionId;		
			this.fechaAprobacion = fechaAprobacion;			
			this.sistemaId = sistemaId;
			this.nombreSistema=nombreSistema;
			this.version=version;
		}
		
		

		public SreSistemasCertificacionEstado() {
		
		}
		
		public long getSolicitudCertificacionId() {
			return solicitudCertificacionId;
		}

		public void setSolicitudCertificacionId(long solicitudCertificacionId) {
			this.solicitudCertificacionId = solicitudCertificacionId;
		}

		public long getPersonaContribuyenteId() {
			return personaContribuyenteId;
		}
		public void setPersonaContribuyenteId(long personaContribuyenteId) {
			this.personaContribuyenteId = personaContribuyenteId;
		}
		public int getEstadoSolicitudCertificacionId() {
			return estadoSolicitudCertificacionId;
		}
		public void setEstadoSolicitudCertificacionId(int estadoSolicitudCertificacionId) {
			this.estadoSolicitudCertificacionId = estadoSolicitudCertificacionId;
		}
		public Long getSistemaId() {
			return sistemaId;
		}
		public void setSistemaId(Long sistemaId) {
			this.sistemaId = sistemaId;
		}
		public SreSistemas getNombreSistema() {
			return nombreSistema;
		}
		public void setNombreSistema(SreSistemas nombreSistema) {
			this.nombreSistema = nombreSistema;
		}
		public SreSistemas getVersion() {
			return version;
		}
		public void setVersion(SreSistemas version) {
			this.version = version;
		}
		
		public Date getFechaAprobacion() {
			return fechaAprobacion;
		}
		public void setFechaAprobacion(Date fechaAprobacion) {
			this.fechaAprobacion = fechaAprobacion;
		}
		
		@Override		
		public String toString() {
			return "SreFacEntSistemasCertificacionEstado [solicitudCertificacionId=" + solicitudCertificacionId
					+ ", personaContribuyenteId="+ personaContribuyenteId 
					+ ", estadoSolicitudCertificacionId=" + estadoSolicitudCertificacionId 				
					+ ",sistemaId=" + sistemaId + ",nombreSistema="+ nombreSistema 
					+ ",version ="+ version + "]";
			
		}
}
