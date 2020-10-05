package ru.max.bk252_with_coroutines.Internet

import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*

class SSLTrustManager {

    private var origTrustmanager: X509TrustManager? = null

    fun SSLTrustManager() {
        try {
            val tmf: TrustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            tmf.init(null as KeyStore?)
            val trustManagers: Array<TrustManager> = tmf.getTrustManagers()
            origTrustmanager = trustManagers[0] as X509TrustManager
        } catch (ex: Exception) {
        }
    }

    fun GetSocketFactory(): SSLSocketFactory? {
        return try {
            val wrappedTrustManagers: Array<TrustManager> = arrayOf<TrustManager>(
                object : X509TrustManager {

                    override fun checkClientTrusted(
                        certs: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                        try {
                            origTrustmanager?.checkClientTrusted(certs, authType)
                        } catch (e: CertificateException) {
                        }
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        certs: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                        try {
                            origTrustmanager?.checkServerTrusted(certs, authType)
                        } catch (ex: Exception) {
                        }
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        TODO("Not yet implemented")
                    }
                }
            )
            val sslContext: SSLContext = SSLContext.getInstance("TLS")
            sslContext.init(null, wrappedTrustManagers, SecureRandom())
            sslContext.getSocketFactory()
        } catch (ex: Exception) {
            null
        }
    }

}