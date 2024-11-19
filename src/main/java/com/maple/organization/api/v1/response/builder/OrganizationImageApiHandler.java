package com.maple.organization.api.v1.response.builder;

import com.alviss.commons.exception.BadRequestException;
import com.alviss.commons.validator.CustomValidator;
import com.maple.organization.api.v1.request.OrganizationImageRequest;
import com.maple.organization.service.OrganizationImageService;
import com.maple.organization.service.model.OrganizationImageEntity;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrganizationImageApiHandler {

  private final OrganizationImageService organizationImageService;
  private final CustomValidator validator;

  private static final int THUMBNAIL_SIZE = 128;
  private static final String IMAGE_FORMAT = "jpeg";

  public ResponseEntity<byte[]> handleGetFullSize(final String userAccountId) {
    val entity = organizationImageService.get(userAccountId);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
        .body(entity.getFullSize());
  }

  public ResponseEntity<byte[]> handleGetThumbnail(final String userAccountId) {
    val entity = organizationImageService.get(userAccountId);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
        .body(entity.getThumbnail());
  }

  public void handlePost(final String organizationId, final OrganizationImageRequest request)
      throws IOException {
    val originalImage = ImageIO.read(request.getImage().getInputStream());
    val thumbnail = toThumbnail(originalImage);

    val fullSizeImage = toByteArray(originalImage);
    val thumbnailImage = toByteArray(thumbnail);
    val entity =
        OrganizationImageEntity.builder()
            .organizationId(organizationId)
            .fullSize(fullSizeImage)
            .thumbnail(thumbnailImage)
            .build();
    validate(entity);
    organizationImageService.save(entity);
  }

  private BufferedImage toThumbnail(final BufferedImage originalImage) {
    val resizedImage =
        new BufferedImage(THUMBNAIL_SIZE, THUMBNAIL_SIZE, BufferedImage.TYPE_INT_RGB);
    resizedImage.getGraphics().drawImage(originalImage, 0, 0, THUMBNAIL_SIZE, THUMBNAIL_SIZE, null);
    return resizedImage;
  }

  private byte[] toByteArray(BufferedImage image) throws IOException {
    val outputStream = new ByteArrayOutputStream();
    ImageIO.write(image, IMAGE_FORMAT, outputStream);
    return outputStream.toByteArray();
  }

  public void handlePatch(final String userAccountId, final OrganizationImageRequest request)
      throws IOException {
    val entity = organizationImageService.get(userAccountId);
    if (request.getImage() != null) {
      val originalImage = ImageIO.read(request.getImage().getInputStream());
      val thumbnail = toThumbnail(originalImage);

      val fullSizeImage = toByteArray(originalImage);
      val thumbnailImage = toByteArray(thumbnail);
      entity.setFullSize(fullSizeImage);
      entity.setThumbnail(thumbnailImage);
    }
    validate(entity);
    organizationImageService.save(entity);
  }

  public void handleDelete(final String userAccountId) {
    val entity = organizationImageService.get(userAccountId);
    organizationImageService.delete(entity);
  }

  private void validate(final OrganizationImageEntity entity) {
    val errors = validator.validate(entity);
    if (!errors.isEmpty()) {
      throw new BadRequestException("There are some validation errors", errors);
    }
  }
}
