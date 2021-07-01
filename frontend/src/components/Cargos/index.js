
import React, { useEffect, useState } from 'react';
import './Cargos.css';
import { Table, Button, Modal, Input, Row, Col } from 'antd';

import api from '../../services/api';

function Cargos() {

	const [isModalVisible, setIsModalVisible] = useState(false);
	const [input, setInput] = useState("");

	const [allCargos, setAllCargos] = useState([]);

	const columns = [
		{
			title: 'Nome',
			dataIndex: 'nome',
			key: 'nome',
		}
	];


	const showModal = () => {
		setIsModalVisible(true);
	};

	const handleOk = async () => {
		setIsModalVisible(false);

		await adicionarCargo(input);

		setInput("");

		await buscarCargos();
	};

	const handleCancel = () => {
		setIsModalVisible(false);
	};

	//API
	async function buscarCargos() {
		try {
			const { data: cargos } = await api.get('/cargos?ordem=asc');

			if (cargos) {
				setAllCargos(cargos);
			}
		} catch (error) {
			console.log("Ops ..", error);
		}
	}

	async function adicionarCargo(nomeCargo) {
		try {

			//JSON SERVER
			let ultimaKey = allCargos.slice(-1).pop();

			let cargo = {
				id: ultimaKey.id + 1,
				key: ultimaKey.key + 1,
				nome: nomeCargo
			};

			await api.post('/cargos', cargo);

		} catch (error) {
			console.log("Ops ..", error);
		}
	}



	useEffect(() => {
		buscarCargos();
	}, []);

	return (
		<div>
			<Row justify="end">
				<Col span={6}>
					<Button type="primary" onClick={showModal}>
						Criar cargo
					</Button>
				</Col>
			</Row>

			<Modal
				title="Criar cargo"
				visible={isModalVisible}
				onOk={handleOk}
				okText="Salvar"
				onCancel={handleCancel}
				cancelText="Cancelar"
			>
				<Input
					placeholder="Digite o nome do cargo"
					onChange={(t) => { setInput(t.target.value) }}
					value={input}
				/>
			</Modal>
			<br />
			{allCargos.length > 0 && <Table
				dataSource={allCargos}
				columns={columns} />}

		</div>
	);
}

export default Cargos;
