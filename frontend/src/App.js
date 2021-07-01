
import React, { useState } from 'react';
import './App.css';
import { Layout, Menu, Breadcrumb } from 'antd';
import Title from 'antd/lib/typography/Title';

import Cargos from './components/Cargos';

const { Header, Footer, Sider, Content } = Layout;

function App() {

	const [state, setState] = useState({
		key: "cargos",
		breadcrumb: "Cargos"
	});

	function menuSelecionado(menu) {
		setState({
			key: menu.key,
			breadcrumb: menu.item.props.title
		})
	}

	function renderizarConteudo(key) {

		switch (key) {
			default:
				return <Cargos />

		}
	}

	return (
		<div className="App">
			<Layout>
				<Header style={{ padding: 10 }}>
					<Title style={{ color: 'white' }} level={3}>Desafio Fundecc</Title>
				</Header>
				<Layout>
					<Sider>
						<Menu
							defaultSelectedKeys={['cargos']}
							mode="inline"
							onSelect={(menu) => menuSelecionado(menu)}
						>
							<Menu.Item key='cargos' title="Cargos">
								Cargos
							</Menu.Item>

						</Menu>
					</Sider>
					<Layout>
						<Content style={{ padding: '0 25px' }}>
							<Breadcrumb style={{ margin: '16px 0' }}>
								<Breadcrumb.Item>{state.breadcrumb}</Breadcrumb.Item>
							</Breadcrumb>
							<div style={{ background: '#fff', padding: 24, minHeight: 580 }}>
								{renderizarConteudo(state.key)}
							</div>
						</Content>
						<Footer style={{ textAlign: 'center' }}>Fundecc@2021</Footer>
					</Layout>
				</Layout>
			</Layout>
		</div>
	);
}

export default App;
