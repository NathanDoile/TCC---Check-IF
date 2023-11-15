import "./tela-registrar-chegada-aluno.screen.css";
import {
  Borda,
  LogoInicial,
  Botao,
  Input,
  TituloTelasIniciais,
} from "../../component";
import cameraIcone from "../../../assets/images/Camera-branco.svg";
import { toast } from "react-toastify";
import Webcam from "react-webcam";
import { useRef, useCallback, useState, useEffect } from "react";
import {
  useLerCracha,
  useObterProfessores,
  useRegistrarChegadaAtrasadaCracha,
} from "../../../hooks";

export function TelaRegistrarChegadaAluno() {
  const { lerCracha } = useLerCracha();

  const { registrarChegadaAtrasadaCracha } =
    useRegistrarChegadaAtrasadaCracha();

  const [formInput, setFormInput] = useState({
    professor: 0,
    disciplina: "",
    motivo: "",
    matricula: "",
  });

  const [outroMotivo, setOutroMotivo] = useState(false);

  const [capturarCodigoBarras, setCapturarCodigoBarras] = useState(false);

  const webcamRef = useRef(null);

  const [imgSrc, setImgSrc] = useState(null);

  const { obterProfessores } = useObterProfessores();

  const [professores, setProfessores] = useState([]);

  const [nomesProfessores, setNomesProfessores] = useState([]);

  const [idsProfessores, setIdsProfessores] = useState([]);

  const motivos = [
    "Selecione",
    "Despertador não tocou",
    "Problema no trânsito",
    "Outro",
  ];

  function handleChange(event) {
    const { name, value } = event.target;

    if (name === "motivo" && value === "Outro") {
      setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: "" }));

      setOutroMotivo(true);
    } else if (name === "outroMotivo") {
      setFormInput((oldFormInput) => ({ ...oldFormInput, motivo: value }));
    } else if (name === "motivo" && value !== "Outro" && outroMotivo) {
      setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }));

      setOutroMotivo(false);
    } else {
      setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }));
    }
  }

  async function registrarChegada(event) {
    event.preventDefault();

    if (
      formInput.professor === 0 ||
      formInput.motivo === "Selecione" ||
      formInput.matricula === "" ||
      formInput.motivo === ""
    ) {
      toast.error("Preencha todos os campos obrigatórios!");
    } else {
      registrarChegadaAtrasadaCracha(
        formInput.motivo,
        formInput.disciplina,
        formInput.matricula,
        formInput.professor
      );
    }
  }

  const capture = useCallback(() => {
    const imageSrc = webcamRef.current.getScreenshot();

    setImgSrc(imageSrc);
  }, [webcamRef]);

  async function concluirLerCracha() {
    try {
      const response = await lerCracha(imgSrc.substring(23));

      setCapturarCodigoBarras(false);

      setFormInput((oldFormInput) => ({
        ...oldFormInput,
        matricula: response.matricula,
      }));
    } catch (error) {
      setImgSrc(null);
    }
  }

  useEffect(() => {
    async function obter() {
      setProfessores([]);

      const response = await obterProfessores();

      setProfessores(response);
    }

    obter();
  }, []);

  useEffect(() => {
    setNomesProfessores([]);
    setIdsProfessores([]);

    professores.forEach((professor) => {
      setNomesProfessores((oldNomesProfessores) => [
        ...oldNomesProfessores,
        professor.nome,
      ]);
      setIdsProfessores((oldIdsProfessores) => [
        ...oldIdsProfessores,
        professor.id,
      ]);
    });
  }, [professores]);

  return (
    <section className="section-registrar-chegada">
      <Borda posicao="superior" />

      <main className="main-registrar-chegada">
        {!capturarCodigoBarras ? (
          <>
            <LogoInicial />

            <TituloTelasIniciais>
              Registre sua chegada atrasada
            </TituloTelasIniciais>

            <span className="container-botao-camera">
              <Botao
                cor="verde"
                onClick={() => {
                  setCapturarCodigoBarras(true);
                  setImgSrc(null);
                }}
              >
                <img className="icone-camera" src={cameraIcone} alt="câmera" />
                <p className="legenda-botao-camera">
                  {formInput.matricula === ""
                    ? "Ler crachá"
                    : formInput.matricula}
                </p>
              </Botao>
              <span className="input-obrigatorio">*</span>
            </span>

            <form
              className="form-registrar-chegada-atrasada"
              onSubmit={registrarChegada}
            >
              <span className="form-span-inputs">
                <Input
                  isSelect={true}
                  legenda={"Professor"}
                  isObrigatorio={true}
                  name={"professor"}
                  handleChange={handleChange}
                  opcoes={nomesProfessores}
                  values={idsProfessores}
                />

                <Input
                  legenda={"Disciplina"}
                  name={"disciplina"}
                  handleChange={handleChange}
                  type={"text"}
                />
              </span>

              <span className="form-span-inputs">
                <Input
                  isSelect={true}
                  legenda={"Motivo"}
                  isObrigatorio={true}
                  name={"motivo"}
                  handleChange={handleChange}
                  opcoes={motivos}
                  values={motivos}
                />

                {outroMotivo ? (
                  <Input
                    legenda={"Outro"}
                    name={"outroMotivo"}
                    isObrigatorio={true}
                    handleChange={handleChange}
                    type={"text"}
                  />
                ) : null}
              </span>

              <Botao cor="amarelo">Registrar</Botao>
            </form>
          </>
        ) : null}
      </main>

      {capturarCodigoBarras ? (
        <div className="modal-camera">
          {imgSrc ? (
            <img src={imgSrc} alt="webcam" />
          ) : (
            <Webcam
              height={600}
              width={600}
              ref={webcamRef}
              screenshotFormat="image/jpeg"
              screenshotQuality={1}
            />
          )}

          {!imgSrc ? (
            <Botao cor="verde" onClick={capture}>
              Capturar foto
            </Botao>
          ) : (
            <>
              <Botao
                cor="verde"
                onClick={() => {
                  setImgSrc(null);
                }}
              >
                Tentar novamente
              </Botao>
              <Botao cor="amarelo" onClick={concluirLerCracha}>
                Concluir
              </Botao>
            </>
          )}
        </div>
      ) : null}

      <Borda posicao="inferior" />
    </section>
  );
}
