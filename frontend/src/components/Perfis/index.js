
import React, { useEffect, useState } from 'react';
import './Perfis.css';
import { Table, Button, Modal, Input, Row, Col } from 'antd';

import api from '../../services/api';

function Perfis() {

	const [isModalVisible, setIsModalVisible] = useState(false);

	const [dataModal, setDataModal] = useState({
		title: "Criar novo perfil",
		itemEdit: undefined
	});

	const [input, setInput] = useState("");

	const [allPerfis, setAllPerfis] = useState([]);

	const columns = [
		{
			title: 'Nome',
			dataIndex: 'nome',
			key: 'nome',
		},
		{
			title: 'Deletar',
			dataIndex: 'remover',
			key: 'deletar',
			width: '25%',
			align: 'center',
			render: (remover, item) => (
				<>
					<Button type="default" style={{ marginRight: 10 }} onClick={() => {

						setDataModal({
							title: "Editar perfil",
							itemEdit: item
						});

						setInput(item.nome);

						setIsModalVisible(true);

					}}>
						Editar
					</Button>

					{
						remover && <Button type="danger" onClick={() => removerPerfil(item.id)}>
							Remover
						</Button>
					}
				</>
			)
		}
	];


	const showModal = () => {
		setIsModalVisible(true);
		setInput("");
		dataModal.itemEdit = undefined;
		setDataModal(dataModal);
	};

	const handleOk = async () => {

		setIsModalVisible(false);

		if (dataModal.itemEdit && dataModal.itemEdit !== input) {
			dataModal.itemEdit.nome = input;
			await editarPerfil(dataModal.itemEdit);
		} else {
			await adicionarPerfil(input);
		}

		setInput("");
		await buscarPerfis();

	};

	const handleCancel = () => {
		setIsModalVisible(false);
	};

	//API
	async function buscarPerfis() {
		try {

			const { data: perfis } = await api.get('/perfis');

			if (perfis) {
				setAllPerfis(perfis);
				console.log("perfis API", perfis);
			}

		} catch (error) {
			console.log("Ops ..", error);
		}
	}

	async function adicionarPerfil(nomePerfil) {
		try {

			//JSON SERVER
			let ultimaKey = (allPerfis && allPerfis.slice(-1).pop()) || 1;

			let perfil = {
				id: ultimaKey.id + 1,
				key: ultimaKey.key + 1,
				nome: nomePerfil
			};

			await api.post('/perfis', perfil);

		} catch (error) {
			console.log("Ops ..", error);
		}
	}

	async function removerPerfil(id) {
		try {

			await api.delete('/perfis/' + id);
			await buscarPerfis();

		} catch (error) {
			console.log("Ops ..", error);
		}
	}

	async function editarPerfil(item) {
		try {

			await api.put('/perfis/' + item.key, item);
			await buscarPerfis();

		} catch (error) {
			console.log("Ops ..", error);
		}
	}

	useEffect(() => {
		buscarPerfis();
	}, []);

	return (
		<div>
			<Row justify="end">
				<Col span={6}>
					<Button type="primary" onClick={showModal}>
						Criar perfil
					</Button>
				</Col>
			</Row>

			<Modal
				title={dataModal.title}
				visible={isModalVisible}
				onOk={handleOk}
				okText="Salvar"
				onCancel={handleCancel}
				cancelText="Cancelar"
			>
				<Input
					placeholder="Digite o nome do perfil"
					onChange={(t) => { setInput(t.target.value) }}
					value={input}
				/>
			</Modal>

			<br />
			{allPerfis.length > 0 && <Table
				dataSource={allPerfis}
				bordered
				size="middle"
				columns={columns} />}

		</div>
	);
}

export default Perfis;
