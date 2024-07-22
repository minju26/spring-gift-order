package gift.product.dto;

import gift.product.entity.Product;

public record ProductResDto(
        Long id,
        String name,
        Integer price,
        String imageUrl,
        String category
) {
    public ProductResDto(Product product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getImageUrl(), (product.getCategory() != null) ? product.getCategory().getName() : "");
    }
}