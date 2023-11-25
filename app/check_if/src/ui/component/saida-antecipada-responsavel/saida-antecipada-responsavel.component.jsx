import "./saida-antecipada-responsavel.component.css";

export function SaidaAntecipadaResponsavel({
  nome,
  turma,
  matricula,
  data,
  hora,
}) {
  return (
    <div className="div-saida-antecipada-responsavel">
      <span className="saida-antecipada-responsavel-nome">{nome}</span>
      <span className="saida-antecipada-responsavel-turma">{turma}</span>
      <span className="saida-antecipada-responsavel-matricula">
        {matricula}
      </span>
      <span className="saida-antecipada-responsavel-data">{data}</span>
      <span className="saida-antecipada-responsavel-hora">{hora}</span>
    </div>
  );
}
