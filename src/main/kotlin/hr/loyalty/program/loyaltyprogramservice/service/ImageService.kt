package hr.loyalty.program.loyaltyprogramservice.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@Service
class ImageService {
    private val root = Paths.get("images")

    fun saveImage(imageFile: MultipartFile): String {
        val extension = imageFile.originalFilename!!.substring(
            imageFile.originalFilename!!.lastIndexOf(".") + 1)
        val imageUri = "${UUID.randomUUID()}.$extension"
        saveImageFile(imageUri, imageFile)
        return imageUri
    }

    private fun saveImageFile(imageUri: String, imageFile: MultipartFile) {
        createRootDir()
        Files.copy(imageFile.inputStream, root.resolve(imageUri))
    }

    fun getImageFile(imageFileName: String): ByteArray {
        return Files.readAllBytes(root.resolve(imageFileName))
    }

    private fun createRootDir() {
        val rootFile = root.toFile()
        if (!rootFile.exists()) {
            if (!rootFile.mkdir()) {
                throw IOException()
            }
        }
    }
}