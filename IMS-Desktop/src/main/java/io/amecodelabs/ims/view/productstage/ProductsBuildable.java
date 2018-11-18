package io.amecodelabs.ims.view.productstage;

import io.amecodelabs.ims.view.base.BuildableWindow;
import io.amecodelabs.ims.view.base.PrimaryStage;
import io.amecodelabs.ims.view.base.Window;
import io.amecodelabs.ims.view.base.WindowBuilder;
import io.amecodelabs.ims.view.context.ApplicationContext;

public class ProductsBuildable implements BuildableWindow {
	private PrimaryStage primary;
	
	public ProductsBuildable(PrimaryStage primary) {
		this.primary = primary;
	}

	@Override
	public Window build() {
		var app = ApplicationContext.getInstance();
		WindowBuilder builder = new WindowBuilder("/view/StageProducts.fxml");
		
		return builder
			.setIcon(app.getIcon())
			.setTitle(app.getName() + " - " + "providers")
			.setPrimaryStage(primary)
			.undecorated()
			.build();
	}

}