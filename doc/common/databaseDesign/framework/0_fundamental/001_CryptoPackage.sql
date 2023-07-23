/*
 * http://www.nazmulhuda.info/encryption-decryption-using-dbms_crypto
 */
/*
 * Grant privileges
 *		login as sys
 * 			grant execute on sys.dbms_crypto to alpaca;
 */

/*
 * Create Package
 */
drop package crypto;

create or replace package crypto as
	function crypto_enc(p_plaintext varchar2) return raw deterministic;
	function crypto_dec(p_encryptedtext raw) return varchar2 deterministic;
end;

create or replace package body crypto as
	encryption_type pls_integer := dbms_crypto.encrypt_des + dbms_crypto.chain_cbc + dbms_crypto.pad_pkcs5;
	encryption_key  raw(32) := utl_raw.cast_to_raw('DSZebraProject');

	function crypto_enc(p_plaintext varchar2) return raw deterministic is
		encrypted_raw raw (2000);

		begin
			encrypted_raw := dbms_crypto.encrypt(
				src => utl_raw.cast_to_raw(p_plaintext),
				typ => encryption_type,
				key => encryption_key
			);

			return encrypted_raw;
	end crypto_enc;

	function crypto_dec(p_encryptedtext raw) return varchar2 deterministic is
		decrypted_raw raw (2000);

		begin
			decrypted_raw := dbms_crypto.decrypt(
				src => p_encryptedtext,
				typ => encryption_type,
				key => encryption_key
			);

			return (utl_raw.cast_to_varchar2 (decrypted_raw));
	end crypto_dec;
end;