from cryptography.hazmat.primitives import serialization, hashes
from cryptography.hazmat.primitives.asymmetric import rsa, padding
from cryptography.hazmat.backends import default_backend
from cryptography.fernet import Fernet

class User:
    def __init__(self, nome_usuario, senha):
        self.nome_usuario = nome_usuario
        self.senha = senha

class Admin(User):
    def __init__(self, nome_usuario, senha):
        super().__init__(nome_usuario, senha)
        self.permissoes = ['criar_usuario', 'apagar_usuario', 'editar_info_usuario', 'ver_info_usuario']

class Usuario_Padrao(User):
    def __init__(self, nome_usuario, senha):
        super().__init__(nome_usuario, senha)
        self.permissoes = ['ver_info_proprio', 'editar_info_proprio']

class Convidado(User):
    def __init__(self, nome_usuario, senha):
        super().__init__(nome_usuario, senha)
        self.permissoes = ['ver_info_publica']

class SistemaAutenticacao:
    def __init__(self):
        self.usuarios = {}
        self.private_key = rsa.generate_private_key(public_exponent=65537, key_size=2048, backend=default_backend())
        self.cipher_suite = Fernet(Fernet.generate_key())

    def registrar_usuario(self, usuario):
        senha_cifrada = self.encriptar_senha(usuario.senha)
        nome_usuario_cifrado = self.encriptar_usuario(usuario.nome_usuario)  
        self.usuarios[nome_usuario_cifrado] = {'senha': senha_cifrada, 'tipo': type(usuario)}

    def autenticar(self, nome_usuario, senha):
        for nome_usuario_cifrado, info_usuario in self.usuarios.items():
            nome_usuario_descriptografado = self.decriptar_usuario(nome_usuario_cifrado) 
            if nome_usuario_descriptografado == nome_usuario:
                senha_cifrada = info_usuario['senha']
                return self.decriptar_senha(senha_cifrada) == senha
        return False

    def obter_permissoes_usuario(self, nome_usuario):
        nome_usuario_cifrado = self.encriptar_usuario(nome_usuario) 
        if nome_usuario_cifrado in self.usuarios:
            tipo_usuario = self.usuarios[nome_usuario_cifrado]['tipo']
            if tipo_usuario == Admin:
                print ("Criar usuario, apagar usuario, editar info usuario e ver info usuario")
            elif tipo_usuario == Usuario_Padrao:
                print ("Ver info proprio e editar info proprio")
            elif tipo_usuario == Convidado:
                print ("ver info publica")
        return []

    def imprimir_senhas_criptografadas(self):
        print("Senhas Criptografadas:")
        for nome_usuario_cifrado, info_usuario in self.usuarios.items():
            senha_cifrada = info_usuario['senha']
            nome_usuario = self.decriptar_usuario(nome_usuario_cifrado) 
            print(' ')
            print(f"Usuário: {nome_usuario}, Senha Criptografada: {senha_cifrada.hex()}")
            
    def imprimir_usuarios_criptografados(self):
        print("Usuários Criptografados:")
        for nome_usuario_cifrado in self.usuarios.keys():
            nome_usuario = self.decriptar_usuario(nome_usuario_cifrado)
            print(' ')
            print(f"Nome do usuário: {nome_usuario}, Nome criptografado: {nome_usuario_cifrado}")

    # criptografia assimétrica usando RSA - OAEP (Optimal Asymmetric Encryption Padding)
    def encriptar_senha(self, senha):
        return self.private_key.public_key().encrypt(
            senha.encode(),
            padding.OAEP(
                mgf=padding.MGF1(algorithm=hashes.SHA256()),
                algorithm=hashes.SHA256(),
                label=None
            )
        )

    def decriptar_senha(self, senha_cifrada):
        return self.private_key.decrypt(
            senha_cifrada,
            padding.OAEP(
                mgf=padding.MGF1(algorithm=hashes.SHA256()),
                algorithm=hashes.SHA256(),
                label=None
            )
        ).decode()

    # Funções para criptografar e descriptografar o nome do usuário em criptografia simétrica Fernet
    def encriptar_usuario(self, nome_usuario):
        return self.cipher_suite.encrypt(nome_usuario.encode())

    def decriptar_usuario(self, nome_usuario_cifrado):
        return self.cipher_suite.decrypt(nome_usuario_cifrado).decode()

# Main
sistema_autenticacao = SistemaAutenticacao()

administrador = Admin("admin", "senha_admin")
usuario_padrao = Usuario_Padrao("usuario", "senha_usuario")
convidado = Convidado("convidado", "senha_convidado")

sistema_autenticacao.registrar_usuario(administrador)
sistema_autenticacao.registrar_usuario(usuario_padrao)
sistema_autenticacao.registrar_usuario(convidado)

print("Sistema de autenticação:")
nome_usuario = input("Nome de usuario: ")
senha = input("Senha: ")

if sistema_autenticacao.autenticar(nome_usuario, senha):
    print("Autenticação bem-sucedida. Permissões do usuário:")
    sistema_autenticacao.obter_permissoes_usuario(nome_usuario)
    if nome_usuario == 'admin':
        print('----------------------------------')
        sistema_autenticacao.imprimir_senhas_criptografadas()
        print('----------------------------------')
        sistema_autenticacao.imprimir_usuarios_criptografados()
else:
    print("Autenticação falhou.")